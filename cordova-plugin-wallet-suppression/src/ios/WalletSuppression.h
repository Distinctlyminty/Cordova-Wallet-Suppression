
/**
 * @author James Millar
 *
 */

#import <Cordova/CDV.h>
#import <PassKit/PassKit.h>

@interface WalletSuppression : CDVPlugin

- (void)enableWallet:(CDVInvokedUrlCommand*)command;
- (void)disableWallet:(CDVInvokedUrlCommand*)command;

@end
