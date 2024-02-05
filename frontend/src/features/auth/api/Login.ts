export interface SignInModel {
  username: string;
  password: string;
}

interface SignInResponse {}

export const signInRequest = async (
  req: SignInModel
): Promise<SignInResponse> =>
  await fetch(`${import.meta.env.VITE_API}/users/login`, {
    method: 'POST',
    body: JSON.stringify(req),
    headers: {
      'Content-Type': 'application/json',
    },
  });
