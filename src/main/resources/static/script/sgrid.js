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
                this.data.forEach(f => f._status = f._status || 'SELECT');
				
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

        markDelete = row => {
                if(row){
                        row._status = 'DELETE';
                        this.#refresh();
                }
        };

        getData = () => this.data;
	
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
		let newData = {
			_status: 'INSERT',
			projectId: '',
			projectName: '',
			description: ''
		}
		this.data.push(newData);
		this.el.bodyTb.insertBefore(this.#createBodyRow(newData, 0), this.el.bodyTb.firstChild);
	}
	
        #createBodyRow(row, rIdx){

            let tr = document.createElement('tr');
                tr.className = row._status?.toLocaleLowerCase();

            this.fields.forEach((field, fIdx) => tr.appendChild(this.#createBodyRowCell(row, rIdx, field, tr)));

            return tr;
        }
	
        #createBodyRowCell = (row, rIdx, field, tr) => {

            let tag = null, td = null, div = null;

            td = document.createElement('td');
            div = document.createElement('div');

                switch(field.type){
                case 'text':
                        tag = document.createElement('span');
                tag.setAttribute('name', field.name);
                tag.textContent = row[field.name];
                        break;
                case 'input':
                        tag = document.createElement('input');
                        tag.setAttribute('type', 'text');
                tag.setAttribute('name', field.name);
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
                        tag.addEventListener('click', ev => {
                                ev.preventDefault();
                                if(typeof field.onClick === 'function'){
                                        field.onClick({row, rIdx, field, grid:this});
                                }
                        });
                        break;
                }

                div.appendChild(tag);
            td.appendChild(div);

                field.width ? td.style.width = field.width : null;

            return td;
        }
}