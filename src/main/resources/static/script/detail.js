class Detail {
	constructor(){
		
		this.element = {};
		this.el = this.element;
		this.editor = null;

		this.#createDialog();
		this.#createEvent();
	}
	
	call(p){
		this.open(p);	
	}
	
	open(p){
		
		this.editor.setMarkdown('');
		
		if(p.action === 'INSERT'){
				
		}else if(p.action === 'SELECT'){
		
		}else{
			return false;
		}
		
		this.element.dialog.showModal();
	}
	
	/*getDialogElement(){
		return this.this.element.dialog;
	}*/

	editor(){
		return this.editor;
	}
	
	/*attachEditor($editor){
		this.editor = $editor;
	}*/

	/*getElement(name){
		if(name){
			return this.element[name];
		}else{
			return this.element;	
		}
	}*/
	
	#createDialog(){

		this.el.dialog = document.createElement('dialog');
		this.el.dialog.classList.add('dialog', 'full'); 
		this.el.form = document.createElement('form');
		this.el.form.method = 'dialog';

		let buttonArea1 = document.createElement('div');
		buttonArea1.className = 'button-area';
		this.el.btnTagAppend = document.createElement('button');
		this.el.btnTagAppend.textContent = '태그등록';
		this.el.btnDetailInfoOpen = document.createElement('button');
		this.el.btnDetailInfoOpen.textContent = '상세정보 등록';
		this.el.editor = document.createElement('div');

		let buttonArea2 = document.createElement('div');
		buttonArea2.classList.add('button-area', 'center', 'big');
		this.el.btnClose = document.createElement('button');
		this.el.btnClose.value = 'close';
		this.el.btnClose.className = 'gray';
		this.el.btnClose.textContent = '닫기';		
		this.el.btnSave = document.createElement('button');
		this.el.btnSave.className = 'blue';
		this.el.btnSave.textContent = '저장';	

		buttonArea1.append(this.el.btnTagAppend, this.el.btnDetailInfoOpen);
		buttonArea2.append(this.el.btnClose, this.el.btnSave);

		this.el.form.append(buttonArea1, this.el.editor, buttonArea2);
		this.el.dialog.appendChild(this.el.form);
		document.body.appendChild(this.el.dialog);
		
		this.editor = new toastui.Editor({
			el: this.el.editor,
			previewStyle: 'tab',
			height: '100%',
		});
	}

	#createEvent(){

	}
}
window.Detail = Detail;