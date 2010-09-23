zk.$package('simplelabel');

examples.com.foo.SimpleLabel = zk.$extends(zk.Widget, {
	_value : '', // default value
	_cleared: false,
	
	getValue : function() {
		return this._value;
	},

	setValue : function(value) {
		if (this._value != value) {
			
			this._value = value;
			
			if (this.desktop)
				this.$n().innerHTML = zUtl.encodeXML(value);
		}
	},

	bind_ : function(evt) {
		this.$supers('bind_', arguments);
		this.domListen_(this.$n().lastChild, "onClick", '_doClear');
	},

	unbind_ : function(evt) {
		this.domUnlisten_(this.$n().lastChild, "onClick", '_doClear');
		this.$supers('unbind_', arguments);
	},
	
	_doClear: function(evt) {
		
		this._cleared = !(this._cleared);
		
		if(this._cleared) {
			this.$n().firstChild.innerHTML = "";
		} else {
			this.$n().firstChild.innerHTML = this._value;
		}
		
		this.fire("onClear", {cleared: this._cleared});
	}
});
