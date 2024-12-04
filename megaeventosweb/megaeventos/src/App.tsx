import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import EventList from './components/EventList';
import EventDetails from './components/EventDetails';
import RegistrationManagement from './components/RegistrationManagement';
import UserProfile from './components/UserProfile';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <nav className="bg-blue-600 text-white p-4 flex justify-between items-center">
          <Link to="/" className="text-xl font-bold hover:underline">
            MegaEventos InTech
          </Link>
          <div>
            {/* <Link to="/registrations" className="px-4 py-2 hover:underline">
              Inscrições
            </Link> */}
            <Link to="/profile" className="px-4 py-2 hover:underline">
              Meu Perfil
            </Link>
          </div>
        </nav>
        <main className="p-6">
          <Routes>
            <Route path="/" element={<EventList />} />
            <Route path="/event/:id" element={<EventDetails />} />
            {/* <Route path="/registrations" element={<RegistrationManagement />} /> */}
            <Route path="/profile" element={<UserProfile />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
