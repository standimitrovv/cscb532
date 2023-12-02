import { AuthenticationPage } from './features/auth/AuthenticationPage';
import { AuthenticationProvider } from './features/auth/state/AuthenticationProvider';

export const App: React.FunctionComponent = () => {
  return (
    <main>
      <AuthenticationProvider>
        <AuthenticationPage />
      </AuthenticationProvider>
    </main>
  );
};
