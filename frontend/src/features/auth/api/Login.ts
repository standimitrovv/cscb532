export interface SignInModel {
  username: string;
  password: string;
}

export const signInRequest = async (req: SignInModel) =>
  await fetch(`${import.meta.env.VITE_API}/users/login`, {
    method: 'POST',
    body: JSON.stringify(req),
    headers: {
      'Content-Type': 'application/json',
    },
  });
