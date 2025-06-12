window.Storage = class Storage {
	
    constructor(p){
		
		this.data = {};
		this.hooks = {};
		
		if (Array.isArray(p?.data)) {
			
			const promises = p.data
				.map(item => fetch(item.url)
					.then(res => res.json())
					.then(json => ({ name: item.name, payload: json, hooks: item.hooks}))
				);
			
			Promise.all(promises)
				.then(results => results.forEach(({ name, payload }) => this.set(name, payload)))
				.then(() => typeof p.then === 'function' ? p.then() : null)
				.catch(err => console.error('error:', err));
		}
		
		if(Array.isArray(p?.data) === false && typeof p.then === 'function'){
			p.then();
		}
	}
	
	
	set = (name, data) => {		
		this.data[name] = data;
		if(typeof this.hooks[name] === 'object' && this.hooks[name].length > 0){
			for(let h of this.hooks[name]){
				if(typeof h.hook === 'function'){
					h.hook({
						storageName: name, 
						hookName: h.name,
						data: data
					});
				}
			}
		}
	}
	
	appendHook = (storageName, hooks) => {
		this.hooks[storageName] = this.hooks[storageName] || [];		
		for(let h of hooks){
			this.hooks[storageName].push({name: h.name, hook: h.hook});
		}
	}
	
	removeHook = (storageName, hookName) => {
		const list = this.hooks[storageName];
		if (!Array.isArray(list)) return;	  
		this.hooks[storageName] = list.filter(h => h.name !== hookName);
	}
	
	get = name => this.data[name];
	
	getAll = () => this.data;
	
}