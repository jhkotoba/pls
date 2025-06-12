window.sGrid = class simpleGrid {
	
    constructor(parameter){
		
		this.rowIdx = 0;

	    this.fields = parameter.fields;
	    this.el = {
			target: parameter.target,
			head: document.createElement('div'),
			headTb: document.createElement('table'),
			headTr: document.createElement('tr'),
			body: document.createElement('div'),
			bodyTb: document.createElement('table'),
			bodyEmpty: document.createElement('div'),
			pagination: null
		}
		
		this.el.target.classList.add('sgrid');
		this.el.head.classList.add('header');
		this.el.body.classList.add('body');
		
		if(parameter?.pagination){
			this.el.pagination = parameter.pagination;
			this.el.pagination.classList.add('pagination');	
		}
		
		this.el.headTb.appendChild(this.el.headTr);
	    this.el.head.appendChild(this.el.headTb);
	    this.el.body.appendChild(this.el.bodyTb);
	    this.el.body.appendChild(this.el.bodyEmpty);
	    this.el.target.appendChild(this.el.head);
	    this.el.target.appendChild(this.el.body);
		
		if(this.el.pagination !== null){
			this.el.target.appendChild(this.el.pagination);	
		}
		
	    this.data = parameter.data || [];
	    this.data.forEach(f => {
			f._status = f._status || 'SELECT';
			f._index = this.#getRowIdx();
		});
		
		this.event = parameter.event || {};
				
		this.#create(parameter);
	}
	
	setData(data, isReset){
		if(isReset === true){
			this.rowIdx = 0;
		}		
		if (Array.isArray(data)) {
			this.data = data.map(item => {
				item._index = this.#getRowIdx();
				item._status = 'SELECT';
				return item;
			});
			this.#refresh();
		}
	}
	
    prependRow = () => this.#createBodyNewRow();   

    getData = () => this.data;
	
	clear(){
		
	}
	
	#getRowIdx(){
		this.rowIdx++;
		return this.rowIdx;
	}
	
	#create(){
		this.#createHead();		
		this.#createBody();
		this.#createEvent();
	}
	
    #refresh(){
		
		while(this.el.bodyTb.hasChildNodes()){
        	this.el.bodyTb.removeChild(this.el.bodyTb.firstChild);
    	}
		
    	this.data.forEach((row, rIdx) => this.el.bodyTb.appendChild(this.#createBodyRow(row, rIdx)));
    }
	
	#createHead(){
		
		let th = null, div = null, tag = null;		
		
		while(this.el.headTr.hasChildNodes()){
            this.el.headTr.removeChild(this.el.headTr.firstChild);
        }
		
		let fields = this.fields;
		for(let i=0; i<fields.length; i++){

		    let field = fields[i];
		    
		    th = document.createElement('th');
		    div = document.createElement('div');
			tag = document.createElement('span');
			tag.textContent = field.title;
			div.appendChild(tag);

		    th.appendChild(div);
		    this.el.headTr.appendChild(th);
			
			field.width ? th.style.width = field.width : null;
		}
	}
	
	#createBody(){
		
		while(this.el.bodyTb.hasChildNodes()){
            this.el.bodyTb.removeChild(this.el.bodyTb.firstChild);
        }
		
		this.data.forEach((item, idx) => this.el.bodyTb.appendChild(this.#createBodyRow(item, idx)));
	}
	
	#createBodyNewRow(){
		
		let newData = {};
		newData._status = 'INSERT';
		newData._index = this.#getRowIdx();
		
		for(let item of this.fields){
			newData[item.name] = '';
		}
		
		this.data.push(newData);
		this.el.bodyTb.insertBefore(this.#createBodyRow(newData), this.el.bodyTb.firstChild);
	}
	
    #createBodyRow(row){

		let tr = document.createElement('tr');
		tr.className = row._status?.toLocaleLowerCase();
		tr.setAttribute('data-index', row._index);
		this.fields.forEach(field => tr.appendChild(this.#createBodyRowCell(row, field, tr)));

        return tr;
    }
	
    #createBodyRowCell = (row, field, tr) => {

        let tag = null, td = null, div = null;

        td = document.createElement('td');
        div = document.createElement('div');

        switch(field.type){
        case 'text':
			tag = document.createElement('span');
			tag.setAttribute('data-name', field.name);
			tag.setAttribute('data-row-index', row._index);
			tag.textContent = row[field.name];
                break;
        case 'input':
			tag = document.createElement('input');
			tag.setAttribute('type', 'text');
        	tag.setAttribute('data-name', field.name);
			tag.setAttribute('data-row-index', row._index);
        	tag.value = row[field.name];
            tag.addEventListener('input', ev => {
                row[field.name] = ev.target.value;
                if(row._status === 'SELECT'){
					row._status = 'UPDATE';
					tr.className = 'update';
                }
            });
			break;
        case 'button':
		    tag = document.createElement('button');
		    tag.textContent = field.label || 'Button';
			tag.setAttribute('data-name', field.name);
			tag.setAttribute('data-row-index', row._index);		    
		    break;
		default:
			if(field.sequence === true){
				tag = document.createElement('span');
				tag.setAttribute('data-name', field.name);
				tag.setAttribute('data-row-index', row._index);
				tag.textContent = row._index;
			}
			break;
        }

        div.appendChild(tag);
        td.appendChild(div);

		field.width ? td.style.width = field.width : null;
		
		td.setAttribute('data-td-name', field.name);
        return td;
    }
	
	#createEvent(){
		
		this.el.body.addEventListener('click', event => {
			
			let rowIdx = event.target.dataset.rowIndex;
			
			let fnClick = this.event?.click[event.target?.dataset?.name];
			if(typeof fnClick === 'function'){
				fnClick(event, {rowIdx});	
			}
		});
	}
}