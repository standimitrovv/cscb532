import Cookies from 'js-cookie';
import {
  createContext,
  useCallback,
  useEffect,
  useMemo,
  useState,
} from 'react';
import { AuthenticationPage } from '../AuthenticationPage';
import { SignInModel, signInRequest } from '../api/Login';
import { SignUpModel, signUpRequest } from '../api/Register';
import { User } from '../models/User';

export type ISignIn = (model: SignInModel) => Promise<void>;

export type ISignUp = (model: SignUpModel) => Promise<void>;

interface AuthContext {
  user: User | undefined;
  isRegistered: boolean;
  isProcessing: boolean;
  errorMessage: string;
  openSignUpPage: () => void;
  openSignInPage: () => void;
  signIn: ISignIn;
  signUp: ISignUp;
  signOut: () => void;
}

const INITIAL_VALUES: AuthContext = {
  user: undefined,
  isRegistered: true,
  isProcessing: false,
  errorMessage: '',
  openSignUpPage: () => {},
  openSignInPage: () => {},
  signIn: async () => {},
  signUp: async () => {},
  signOut: () => {},
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

  const [user, setUser] = useState<User | undefined>(INITIAL_VALUES.user);

  const [isProcessing, setIsProcessing] = useState<boolean>(
    INITIAL_VALUES.isProcessing
  );

  const [errorMessage, setErrorMessage] = useState<string>(
    INITIAL_VALUES.errorMessage
  );

  const openSignUpPage = () => {
    setIsRegistered(false);
    defaultProcessingAndErrorMessageStates();
  };

  const openSignInPage = useCallback(() => {
    setIsRegistered(true);
    defaultProcessingAndErrorMessageStates();
  }, []);

  const defaultProcessingAndErrorMessageStates = () => {
    setIsProcessing(INITIAL_VALUES.isProcessing);
    setErrorMessage(INITIAL_VALUES.errorMessage);
  };

  const signIn = useCallback(async (model: SignInModel) => {
    setIsProcessing(true);
    try {
      const res = await signInRequest(model);

      if (!res.ok) {
        setErrorMessage('Incorrect username or password');

        return;
      }

      const user: User = await res.json();

      setUser(user);

      Cookies.set('user', JSON.stringify(user), { expires: 1 });
    } catch (err) {
      // TODO
    } finally {
      setIsProcessing(false);
    }
  }, []);

  const signUp = useCallback(
    async (model: SignUpModel) => {
      setIsProcessing(true);
      try {
        const result = await signUpRequest(model);

        if (!result.ok) {
          return;
        }

        openSignInPage();
      } catch (err) {
        // TODO:
      } finally {
        setIsProcessing(false);
      }
    },
    [openSignInPage]
  );

  const signOut = useCallback(() => {
    Cookies.remove('user');

    setUser(undefined);
  }, []);

  useEffect(() => {
    const stringifiedCookiesUser = Cookies.get('user');

    if (!stringifiedCookiesUser?.trim()) {
      return;
    }

    setUser(JSON.parse(stringifiedCookiesUser));
  }, []);

  const authContext: AuthContext = {
    user,
    isRegistered,
    isProcessing,
    errorMessage,
    openSignUpPage,
    openSignInPage,
    signIn,
    signUp,
    signOut,
  };

  const isLoggedIn = useMemo(() => user?.token, [user]);

  return (
    <AuthenticationContext.Provider value={authContext}>
      {isLoggedIn ? children : <AuthenticationPage />}
    </AuthenticationContext.Provider>
  );
};
