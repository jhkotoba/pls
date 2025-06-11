class Project {
	
	constructor(parameter){
		
		parameter = parameter || {};
		
		this.element = {};
		this.el = this.element;
		this.grid = null;
		this.isInit = false;
		this.data = null;
		
		
		if(typeof parameter?.close === 'function'){
			this.fnClose = parameter.close;	
		}else{
			this.fnClose = () => {};
		}
		
		this.data = parameter.data;

		this.#createDialog();
		this.#createEvent();
	}
	
	call = (p) => this.open(p);
	open(p){
		this.element.dialog.showModal();
		if(p.action === 'INIT'){
			this.isInit = true;
		}
	}
	
	setData(data){
		this.data = data;
		this.grid.setData(this.data);
	}
	
    add = () => this.grid.prependRow();

    apply = async () => {
		
		const data = this.grid.getData();		
		let response = await fetch('/project/apply-find', {
            method:'POST',
            headers:{'Content-Type':'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({list: data})
        });
		
		const list = await response.json();
		list.forEach(f => f._status = 'SELECT');
		console.log('list:', list);
		this.grid.setData(list);
    };
	
	#createDialog(){

		this.el.dialog = document.createElement('dialog');
		this.el.dialog.classList.add('dialog', 'full'); 
		this.el.form = document.createElement('form');
		this.el.form.method = 'dialog';
	
		this.el.content = document.createElement('div');
		this.el.content.classList.add('content');
		let controlArea = document.createElement('div');
		controlArea.classList.add('button-area', 'right');
		
		this.el.btnReset = document.createElement('button');
		this.el.btnReset.textContent = '초기화';
		
		this.el.add = document.createElement('button');
		this.el.add.textContent = '추가';
		
		this.el.btnSave = document.createElement('button');
		this.el.btnSave.className = 'blue';
		this.el.btnSave.textContent = '저장';
		controlArea.append(this.el.btnReset, this.el.add, this.el.btnSave)
		
		this.el.grid = document.createElement('div');
		this.el.content.append(controlArea, this.el.grid);
		
		let buttonArea = document.createElement('div');
		buttonArea.classList.add('button-area', 'center', 'big');
		this.el.btnClose = document.createElement('button');
		this.el.btnClose.value = 'close';
		this.el.btnClose.className = 'gray';
		this.el.btnClose.textContent = '닫기';
		
		buttonArea.append(this.el.btnClose);
	
		this.el.form.append(this.el.content, buttonArea);
		this.el.dialog.appendChild(this.el.form);
		document.body.appendChild(this.el.dialog);
		
        this.grid = new window.sGrid({
            target: this.el.grid,
            fields: [
                {title:'순번', sequence: true},
                {title:'프로젝트', name:'projectName', width:'150px', type:'input'},
                {title:'설명', name:'description', type:'input'},
                {title:'삭제', name:'delete', type:'button', width:'60px', label:'삭제'}
            ],
            data: this.data,
            event: {
                click: {
                    delete: async (ev, {rowIdx}) => {
                        ev.preventDefault();
                        const row = this.grid.getData().find(r => String(r._index) === String(rowIdx));
                        if(!row) return;
                        if(row._status === 'INSERT'){
                            this.grid.removeRow(rowIdx);
                            return;
                        }
                        if(!row.projectId) return;
                        if(!confirm('삭제하시겠습니까?')) return;
                        await fetch(`/project/delete/${row.projectId}`, {method:'POST', headers:{'Accept':'application/json'}});
                        this.grid.removeRow(rowIdx);
                    }
                }
            }
        });
	}

	#createEvent(){
		
        this.el.btnReset.addEventListener('click', async ev => {
            ev.preventDefault();
            const res = await fetch('/project/find');
            const list = await res.json();
			
            list.forEach(f => f._status = 'SELECT');
			
            this.grid.data = list;
            this.grid.setData(list);
        });
		
		this.el.add.addEventListener('click', ev => {
			ev.preventDefault();
			this.add();
		});
		
        this.el.btnSave.addEventListener('click', ev => {
            ev.preventDefault();
            this.apply();
        });
		
		this.el.btnClose.addEventListener('click', ev => {	
			console.log('dialog btnClose click');		
			if(this.isInit){
				ev.preventDefault();
				ev.stopPropagation();
				alert('registration required click');	
			}
		});
		
		this.el.dialog.addEventListener('cancel', ev => {
			console.log('dialog cancel');
			if(this.isInit){
				ev.preventDefault();
				ev.stopPropagation();
				alert('registration required cancel');	
			}
		});
		
		
		this.el.dialog.addEventListener('keydown', ev => {
			console.log('dialog keydown');
			if (ev.key === 'Escape' && this.isInit === true) {
				if(this.isInit){
					ev.preventDefault();
					ev.stopPropagation();
					window.alert('registration required cancel');	
				}
			}
		});
	}
	
}
window.Project = Project;
