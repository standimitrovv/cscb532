import { createContext, useCallback, useState } from 'react';
import { AuthenticationPage } from '../AuthenticationPage';
import { SignInModel, signInRequest } from '../api/Login';
import { SignUpModel } from '../api/Register';

export type ISignIn = (model: SignInModel) => Promise<void>;

export type ISignUp = (model: SignUpModel) => Promise<void>;

interface AuthContext {
  // user?: User
  isRegistered: boolean;
  isProcessing: boolean;
  errorMessage: string;
  openSignUpPage: () => void;
  openSignInPage: () => void;
  signIn: ISignIn;
  signUp: ISignUp;
}

const INITIAL_VALUES: AuthContext = {
  // user: undefined,
  isRegistered: true,
  isProcessing: false,
  errorMessage: '',
  openSignUpPage: () => {},
  openSignInPage: () => {},
  signIn: async () => {},
  signUp: async () => {},
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

  // const [user, setUser] = useState<LoginResponse | undefined>(
  //   DEFAULT_STATE_VALUES.USER
  // );

  const [isProcessing, setIsProcessing] = useState<boolean>(
    INITIAL_VALUES.isProcessing
  );

  const [errorMessage, setErrorMessage] = useState<string>(
    INITIAL_VALUES.errorMessage
  );

  const openSignUpPage = () => setIsRegistered(false);

  const openSignInPage = () => setIsRegistered(true);

  const signIn = useCallback(async (model: SignInModel) => {
    setIsProcessing(true);
    try {
      // TODO:
      const res = await signInRequest(model);
      console.log(res);
    } catch (err) {
      // TODO:
    } finally {
      setIsProcessing(false);
    }
  }, []);

  const signUp = useCallback(async () => {
    setIsProcessing(true);
    try {
      // TODO:
    } catch (err) {
      // TODO:
    } finally {
      setIsProcessing(false);
    }
  }, []);

  const authContext: AuthContext = {
    isRegistered,
    isProcessing,
    errorMessage,
    openSignUpPage,
    openSignInPage,
    signIn,
    signUp,
  };

  const isLoggedIn = false;

  return (
    <AuthenticationContext.Provider value={authContext}>
      {isLoggedIn ? children : <AuthenticationPage />}
    </AuthenticationContext.Provider>
  );
};
