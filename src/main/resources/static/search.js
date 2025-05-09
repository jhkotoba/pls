

const resultsList = document.getElementById('resultsList');
const searchButton = document.getElementById('searchButton');
const openButton = document.getElementById('openButton');
const saveButton = document.getElementById('saveButton');

const dialog = document.getElementById('dialog');
const editor = new toastui.Editor({
	el: document.querySelector('#editor'),
	previewStyle: 'tab'
});;

dialog.openModal = function(p){
	
	//let editor = null;
	
	// clean
	editor.setMarkdown('');
	
	
	//
	if(p.action === 'INSERT'){
		
	}else if(p.action === 'SELECT'){
		
		// search
		
		//p.searchId
		//	
	
	}else{
		return false;
	}
	
	
	
	// open
	dialog.showModal();
	
}

resultsList.addEventListener('click', (e) => {
	const li = e.target.closest('li');
	if (!li) return;	
	dialog.openModal({action: 'SELECT'});
});

openButton.addEventListener('click', () => dialog.openModal({action: 'INSERT'}));

// 메뉴 펼치기 접힘 이벤트
document
	.querySelectorAll('.panel-header')
	.forEach(header => header.addEventListener('click', () => header.parentElement.classList.toggle('active')));

// dialog가 Esc 키로 닫힐 때 발생
dialog.addEventListener('cancel', () => {
	
	// clean
	
  // console.log('esc');
  // e.preventDefault();  // 닫힘 방지
});

dialog.addEventListener('close', () => {
	
	// clean
	
  console.log('close returnValue:', dialog.returnValue);
});


saveButton.addEventListener('click', e => {
	e.preventDefault();
	alert(1234);
	//editor.getHTML
	
	
})