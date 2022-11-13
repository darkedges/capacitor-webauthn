import { WebPlugin } from '@capacitor/core';
import { browserSupportsWebAuthn, browserSupportsWebAuthnAutofill, startRegistration } from '@simplewebauthn/browser';
import { PublicKeyCredentialCreationOptionsJSON, RegistrationCredentialJSON } from '@simplewebauthn/typescript-types';

import type { WebAuthnPlugin } from './definitions';

export class WebAuthnWeb extends WebPlugin implements WebAuthnPlugin {

  startRegistration(publicKeyCredentialCreationOptionsJSON: PublicKeyCredentialCreationOptionsJSON): Promise<RegistrationCredentialJSON> {
    return startRegistration(publicKeyCredentialCreationOptionsJSON);
  }

  async isWebAuthnAvailable(): Promise<{ value: boolean }> {
    return this.isAvailable('webauthn');
  }

  async isWebAuthnAutoFillAvailable(): Promise<{ value: boolean }> {
    return this.isAvailable('webauthnautofill');
  }

  private async isAvailable(type: 'webauthn' | 'webauthnautofill'): Promise<{ value: boolean }> {
    let val = false;
    if (type === 'webauthn') {
      val = await browserSupportsWebAuthn();
    }
    if (type === 'webauthnautofill') {
      val = await browserSupportsWebAuthnAutofill();
    }
    return Promise.resolve({ value: val });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

}
