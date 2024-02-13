import { Navbar } from './components/Navbar';
import { AuthenticationProvider } from './features/auth/state/AuthenticationProvider';
import { Shipments } from './features/shipments/Shipments';

export const App: React.FunctionComponent = () => {
  return (
    <main>
      <AuthenticationProvider>
        <body className='p-2'>
          <Navbar />

          <Shipments />
        </body>
      </AuthenticationProvider>
    </main>
  );
};
