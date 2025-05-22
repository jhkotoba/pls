

const documentPopup = document.getElementById('document-popup');
const documentOpen = document.getElementById('document-open');
documentOpen.addEventListener('click', () => documentPopup.showModal());

//
const dtlOpen = document.getElementById('detail-open');
const dtlSave = document.getElementById('detail-save');
const dtlPop = document.getElementById('detail-popup');





const editor = new toastui.Editor({
	el: document.querySelector('#editor'),
	previewStyle: 'tab',
	height: '100%',
});;

//
dtlPop.openModal = function(p){
	
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
	dtlPop.showModal();
	
}

dtlOpen.addEventListener('click', () => dtlPop.openModal({action: 'INSERT'}));

document
	.querySelectorAll('.panel-header')
	.forEach(header => header.addEventListener('click', () => header.parentElement.classList.toggle('active')));
	
// dialog가 Esc 키로 닫힐 때 발생
dtlPop.addEventListener('cancel', () => {
	
	// clean
	
  // console.log('esc');
  // e.preventDefault();  // 닫힘 방지
});

dtlPop.addEventListener('close', () => {
	
	// clean
	
  console.log('close returnValue:', dtlPop.returnValue);
});