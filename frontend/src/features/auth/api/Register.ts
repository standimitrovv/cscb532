export interface SignUpModel {}

interface SignUpResponse {}

export const signUpRequest = async (
  req: SignUpModel
): Promise<SignUpResponse> =>
  await fetch(`${import.meta.env.VITE_API}/users/register`, {
    method: 'POST',
    body: JSON.stringify(req),
    headers: {
      'Content-Type': 'application/json',
    },
  });
