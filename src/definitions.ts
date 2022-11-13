import { PublicKeyCredentialCreationOptionsJSON, RegistrationCredentialJSON } from '@simplewebauthn/typescript-types';

export interface WebAuthnPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  isWebAuthnAvailable(): Promise<{ value: boolean }>;
  isWebAuthnAutoFillAvailable(): Promise<{ value: boolean }>;
  startRegistration(publicKeyCredentialCreationOptionsJSON: PublicKeyCredentialCreationOptionsJSON): Promise<RegistrationCredentialJSON>;
}
