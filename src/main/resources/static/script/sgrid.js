window.sGrid = class simpleGrid {
	
	constructor(parameter){
				
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
		this.data.forEach(f => f._status = 'SELECT');
				
		this.#create(parameter);
	}
	
	setData(data){
		if(typeof data === 'object' && data.length !== undefined){
			this.data = data.map(m => {
				console.log('...');
			});
		}
		this.#refresh();
	}
	
	prependRow = () => this.#createBodyNewRow();
	
	clear(){
		
	}
	
	#create(){
		this.#createHead();		
		this.#createBody();
	}
	
	#refresh(){
		
		while(this.el.bodyTb.hasChildNodes()){
            this.el.bodyTb.removeChild(this.el.bodyTb.firstChild);
        }

	    this.data.forEach((row, rIdx) => this.bodyTb.appendChild(this.#createBodyRow(row, rIdx)));
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
		let newData = {
			_status: 'INSERT'
		}
		this.data.push(newData);
		this.el.bodyTb.insertBefore(this.#createBodyRow(newData, 0), this.el.bodyTb.firstChild);
	}
	
	#createBodyRow(row, rIdx){
		
	    let tr = document.createElement('tr');		
	    this.fields.forEach((field, cIdx) => tr.appendChild(this.#createBodyRowCell(row, rIdx, field, cIdx)));
	    
	    return tr;
	}
	
	#createBodyRowCell = (row, rIdx, cell, cIdx,) => {
	   
	    let type = null, tag = null, td = null, div = null;

	    td = document.createElement('td');
	    div = document.createElement('div');

		tag = document.createElement('span');
        tag.setAttribute('name', cell.name);
        tag.textContent = row[cell.name];
        div.appendChild(tag);
	    td.appendChild(div);
		
		cell.width ? td.style.width = cell.width : null;
	    
	    return td;
	}
}