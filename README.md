# @darkedges/webauthn

WebAuthn

## Install

```bash
npm install @darkedges/webauthn
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`isWebAuthnAvailable()`](#iswebauthnavailable)
* [`isWebAuthnAutoFillAvailable()`](#iswebauthnautofillavailable)
* [`startRegistration(...)`](#startregistration)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### isWebAuthnAvailable()

```typescript
isWebAuthnAvailable() => Promise<{ value: boolean; }>
```

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### isWebAuthnAutoFillAvailable()

```typescript
isWebAuthnAutoFillAvailable() => Promise<{ value: boolean; }>
```

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### startRegistration(...)

```typescript
startRegistration(publicKeyCredentialCreationOptionsJSON: PublicKeyCredentialCreationOptionsJSON) => Promise<RegistrationCredentialJSON>
```

| Param                                        | Type                                                                                                      |
| -------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| **`publicKeyCredentialCreationOptionsJSON`** | <code><a href="#publickeycredentialcreationoptionsjson">PublicKeyCredentialCreationOptionsJSON</a></code> |

**Returns:** <code>Promise&lt;<a href="#registrationcredentialjson">RegistrationCredentialJSON</a>&gt;</code>

--------------------


### Interfaces


#### RegistrationCredentialJSON

A slightly-modified RegistrationCredential to simplify working with ArrayBuffers that
are Base64URL-encoded in the browser so that they can be sent as JSON to the server.

| Prop                         | Type                                                                                                            |
| ---------------------------- | --------------------------------------------------------------------------------------------------------------- |
| **`rawId`**                  | <code><a href="#base64urlstring">Base64URLString</a></code>                                                     |
| **`response`**               | <code><a href="#authenticatorattestationresponsejson">AuthenticatorAttestationResponseJSON</a></code>           |
| **`clientExtensionResults`** | <code><a href="#authenticationextensionsclientoutputsjson">AuthenticationExtensionsClientOutputsJSON</a></code> |
| **`transports`**             | <code>AuthenticatorTransportFuture[]</code>                                                                     |


#### AuthenticatorAttestationResponseJSON

A slightly-modified AuthenticatorAttestationResponse to simplify working with ArrayBuffers that
are Base64URL-encoded in the browser so that they can be sent as JSON to the server.

| Prop                    | Type                                                        |
| ----------------------- | ----------------------------------------------------------- |
| **`clientDataJSON`**    | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`attestationObject`** | <code><a href="#base64urlstring">Base64URLString</a></code> |


#### AuthenticationExtensionsClientOutputsJSON

| Prop               | Type                                                                                                                              |
| ------------------ | --------------------------------------------------------------------------------------------------------------------------------- |
| **`devicePubKey`** | <code><a href="#authenticationextensionsdevicepublickeyoutputsjson">AuthenticationExtensionsDevicePublicKeyOutputsJSON</a></code> |


#### AuthenticationExtensionsDevicePublicKeyOutputsJSON

| Prop                      | Type                                                        |
| ------------------------- | ----------------------------------------------------------- |
| **`authenticatorOutput`** | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`signature`**           | <code><a href="#base64urlstring">Base64URLString</a></code> |


#### PublicKeyCredentialCreationOptionsJSON

A variant of PublicKeyCredentialCreationOptions suitable for JSON transmission to the browser to
(eventually) get passed into navigator.credentials.create(...) in the browser.

| Prop                     | Type                                                                                                              |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------- |
| **`user`**               | <code><a href="#publickeycredentialuserentityjson">PublicKeyCredentialUserEntityJSON</a></code>                   |
| **`challenge`**          | <code><a href="#base64urlstring">Base64URLString</a></code>                                                       |
| **`excludeCredentials`** | <code>PublicKeyCredentialDescriptorJSON[]</code>                                                                  |
| **`extensions`**         | <code><a href="#authenticationextensionsclientinputsfuture">AuthenticationExtensionsClientInputsFuture</a></code> |


#### PublicKeyCredentialUserEntityJSON

| Prop     | Type                |
| -------- | ------------------- |
| **`id`** | <code>string</code> |


#### PublicKeyCredentialDescriptorJSON

| Prop             | Type                                                        |
| ---------------- | ----------------------------------------------------------- |
| **`id`**         | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`transports`** | <code>AuthenticatorTransportFuture[]</code>                 |


#### AuthenticationExtensionsClientInputsFuture

| Prop               | Type                                                                                                                    |
| ------------------ | ----------------------------------------------------------------------------------------------------------------------- |
| **`devicePubKey`** | <code><a href="#authenticationextensionsdevicepublickeyinputs">AuthenticationExtensionsDevicePublicKeyInputs</a></code> |


#### AuthenticationExtensionsDevicePublicKeyInputs

| Prop                     | Type                                                                                        |
| ------------------------ | ------------------------------------------------------------------------------------------- |
| **`attestation`**        | <code><a href="#attestationconveyancepreference">AttestationConveyancePreference</a></code> |
| **`attestationFormats`** | <code>AttestationFormat[]</code>                                                            |


### Type Aliases


#### Base64URLString

An attempt to communicate that this isn't just any string, but a Base64URL-encoded string

<code>string</code>


#### AuthenticatorTransportFuture

A super class of TypeScript's `AuthenticatorTransport` that includes support for the latest
transports. Should eventually be replaced by TypeScript's when TypeScript gets updated to
know about it (sometime after 4.6.3)

<code>'ble' | 'internal' | 'nfc' | 'usb' | 'cable' | 'hybrid'</code>


#### AttestationConveyancePreference

<code>"direct" | "enterprise" | "indirect" | "none"</code>


#### AttestationFormat

<code>'fido-u2f' | 'packed' | 'android-safetynet' | 'android-key' | 'tpm' | 'apple' | 'none'</code>

</docgen-api>
