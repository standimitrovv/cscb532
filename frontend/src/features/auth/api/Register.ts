export interface SignUpModel {
  username: string;
  email: string;
  password: string;
}

export const signUpRequest = async (req: SignUpModel) =>
  await fetch(`${import.meta.env.VITE_API}/users/register`, {
    method: 'POST',
    body: JSON.stringify(req),
    headers: {
      'Content-Type': 'application/json',
    },
  });
