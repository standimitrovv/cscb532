import { createContext, useState } from 'react';

interface AuthContext {
  isRegistered: boolean;
  openSignUpPage: () => void;
  openSignInPage: () => void;
}

const INITIAL_VALUES: AuthContext = {
  isRegistered: true,
  openSignUpPage: () => {},
  openSignInPage: () => {},
};

export const AuthenticationContext = createContext<AuthContext>(INITIAL_VALUES);

interface Props {
  children: JSX.Element;
}

export const AuthenticationProvider: React.FunctionComponent<Props> = ({
  children,
}) => {
  const [isRegistered, setIsRegistered] = useState<boolean>(
    INITIAL_VALUES.isRegistered
  );

  const openSignUpPage = () => setIsRegistered(false);

  const openSignInPage = () => setIsRegistered(true);

  const authContext: AuthContext = {
    isRegistered,
    openSignUpPage,
    openSignInPage,
  };

  return (
    <AuthenticationContext.Provider value={authContext}>
      {children}
    </AuthenticationContext.Provider>
  );
};
