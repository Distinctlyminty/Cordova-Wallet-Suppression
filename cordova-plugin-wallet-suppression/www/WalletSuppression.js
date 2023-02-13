

module.exports = {
	enableWallet: function(success, error){
		cordova.exec(success,error, 'WalletSuppression', 'enableWallet');
	},

	disableWallet: function(success, error) {
		cordova.exec(success, error, 'WalletSuppression', 'disableWallet');
	}
};



