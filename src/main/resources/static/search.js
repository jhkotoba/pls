
//
const srhForm = document.getElementById('search-form');

//
const rstList = document.getElementById('list');

//
const search = document.getElementById('search');
//
const dtlOpen = document.getElementById('detail-open');
const dtlSave = document.getElementById('detail-save');
//
const docOpen = document.getElementById('document-open');
const docSave = document.getElementById('document-save');

//
const dtlPop = document.getElementById('detail-popup');
const docPop = document.getElementById('document-popup');

//
const tagAped = document.getElementById('tag-append'); 
const tagPop = document.getElementById('tag-popup'); 


//
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

// 
rstList.addEventListener('click', (e) => {
	const li = e.target.closest('li');
	if (!li) return;	
	dtlPop.openModal({action: 'SELECT'});
});

// 
search.addEventListener('click', async e => {
	e.preventDefault();
	
	const response = await fetch('/list');		
	let list = await response.json();
	console.log('list==>', list);
	
});



dtlOpen.addEventListener('click', () => dtlPop.openModal({action: 'INSERT'}));

	



// 메뉴 펼치기 접힘 이벤트
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


tagAped.addEventListener('click', e => {
	e.preventDefault();
	tagPop.showModal();
});

dtlSave.addEventListener('click', async e => {
	e.preventDefault();
	
	if(window.confirm('?') === false){
		return false;	
	}
	
	const data  = {
		content:  editor.getMarkdown()
	}
	
	const response = await fetch('/insert', {
		method: 'POST',		
		headers: {"Content-Type": "application/json"},		
		body: JSON.stringify(data)
	});
	
	await response.json();
	
	window.alert('!!');
	editor.setMarkdown('');
	dialog.close();
	
	//editor.getHTML
	
	
})