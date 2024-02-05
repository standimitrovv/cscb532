import { AuthenticationProvider } from './features/auth/state/AuthenticationProvider';

export const App: React.FunctionComponent = () => {
  return (
    <main>
      <AuthenticationProvider>
        <div>Hello world! You are logged in!</div>
      </AuthenticationProvider>
    </main>
  );
};
