class Project {
	
	constructor(parameter){
		
		this.element = {};
		this.el = this.element;
		this.grid = null;
		this.isInit = false;
		this.data = null;
		
		if(parameter?.data){
			this.data = parameter.data;
		}else{
			this.data = [];
		}
		
		this.data = [
			{projectId: 1, projectName: 'pls', description: 'test'},
			{projectId: 2, projectName: 'oe', description: 'test'},
			
		]

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
		
		this.el.btnSave = document.createElement('button');
		this.el.btnSave.className = 'blue';
		this.el.btnSave.textContent = '저장';
		controlArea.append(this.el.btnReset, this.el.btnSave)
		
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
				{title:'ID', name:'projectId', width:'80px'},
				{title:'프로젝트', name:'projectName', width:'150px'},
				{title:'설명', name:'description'},
			],
			data: this.data
		});
	}

	#createEvent(){
		
		this.el.btnReset.addEventListener('click', ev => {
			ev.preventDefault();
			alert('this.el.btnReset');
			
			
		});
		
		this.el.btnSave.addEventListener('click', ev => {
			ev.preventDefault();
			alert('this.el.btnSave');
			
			
		});
		
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
