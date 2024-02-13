import { Navbar } from './components/Navbar';
import { AuthenticationProvider } from './features/auth/state/AuthenticationProvider';

export const App: React.FunctionComponent = () => {
  return (
    <main>
      <AuthenticationProvider>
        <body className='p-2'>
          <Navbar />
        </body>
      </AuthenticationProvider>
    </main>
  );
};
