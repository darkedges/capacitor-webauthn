export interface WebAuthnPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
