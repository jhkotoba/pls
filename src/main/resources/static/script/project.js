class Project {
	
	constructor(){
		
		this.element = {};
		this.el = this.element;
		this.grid = null;
		this.isInit = false;

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
	
	getElement(){
			
	}
	
	#createDialog(){

		this.el.dialog = document.createElement('dialog');
		this.el.dialog.classList.add('dialog', 'full'); 
		this.el.form = document.createElement('form');
		this.el.form.method = 'dialog';
	
		this.el.content = document.createElement('div');
		this.el.content.classList.add('content');
		
		let buttonArea = document.createElement('div');
		buttonArea.classList.add('button-area', 'center', 'big');
		this.el.btnClose = document.createElement('button');
		this.el.btnClose.value = 'close';
		this.el.btnClose.className = 'gray';
		this.el.btnClose.textContent = '닫기';		
		//this.el.btnSave = document.createElement('button');
		//this.el.btnSave.className = 'blue';
		//this.el.btnSave.textContent = '저장';
		
		buttonArea.append(this.el.btnClose);
	
		this.el.form.append(this.el.content, buttonArea);
		this.el.dialog.appendChild(this.el.form);
		document.body.appendChild(this.el.dialog);
		
		this.grid = new window.sGrid({
			target: this.el.content,
			fields: [
				{title:'a', name:'temp',},
				
				
			],
			data: [
				{temp: '111111111111111'},
				{temp: '2222222222222222'},
				
			]
			
		});
	}

	#createEvent(){
		this.el.btnClose.addEventListener('click', ev => {			
			if(this.isInit){
				ev.preventDefault();
				ev.stopPropagation();
				alert('registration required click');	
			}
		});
		
		this.el.dialog.addEventListener('cancel', ev => {			
			if(this.isInit){
				ev.preventDefault();
				ev.stopPropagation();
				alert('registration required cancel');	
			}
		});
		
		
		this.el.dialog.addEventListener('keydown', ev => {
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
