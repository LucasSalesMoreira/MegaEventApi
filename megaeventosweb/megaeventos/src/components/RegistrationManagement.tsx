import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';

interface Registration {
  id: number;
  userName: string;
  eventId: number;
  userEmail: string;
}

interface Event {
  id: number;
  name: string;
}

const RegistrationManagement: React.FC = () => {
  const [registrations, setRegistrations] = useState<Registration[]>([]);
  const [events, setEvents] = useState<Event[]>([]);
  const [selectedEventId, setSelectedEventId] = useState<number | null>(null);

  useEffect(() => {
    // Fetch all events to populate dropdown
    const fetchEvents = async () => {
      try {
        const response = await api.get('/events');
        setEvents(response.data);
      } catch (error) {
        console.error('Error fetching events:', error);
      }
    };

    fetchEvents();
  }, []);

  useEffect(() => {
    // Fetch registrations for the selected event
    if (selectedEventId !== null) {
      const fetchRegistrations = async () => {
        try {
          const response = await api.get(`/registrations?eventId=${selectedEventId}`);
          setRegistrations(response.data);
        } catch (error) {
          console.error('Error fetching registrations:', error);
        }
      };

      fetchRegistrations();
    }
  }, [selectedEventId]);

  const handleRemoveRegistration = async (registrationId: number) => {
    try {
      await api.delete(`/registrations/${registrationId}`);
      setRegistrations((prev) => prev.filter((reg) => reg.id !== registrationId));
    } catch (error) {
      console.error('Error removing registration:', error);
    }
  };

  const handleAddRegistration = async () => {
    const userEmail = prompt('Digite o email do usuário:');
    if (!userEmail || !selectedEventId) return;

    try {
      const response = await api.post('/registrations', {
        eventId: selectedEventId,
        userEmail,
      });
      setRegistrations((prev) => [...prev, response.data]);
    } catch (error) {
      console.error('Error adding registration:', error);
    }
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Gestão de Inscrições</h1>
      
      {/* Event Selector */}
      <div className="mb-4">
        <label htmlFor="event-select" className="block mb-2 font-semibold">Selecione um Evento</label>
        <select
          id="event-select"
          className="border p-2 rounded w-full"
          value={selectedEventId || ''}
          onChange={(e) => setSelectedEventId(Number(e.target.value))}
        >
          <option value="">-- Selecione um Evento --</option>
          {events.map((event) => (
            <option key={event.id} value={event.id}>
              {event.name}
            </option>
          ))}
        </select>
      </div>

      {/* Registration List */}
      {selectedEventId && (
        <>
          <div className="mb-4">
            <button
              onClick={handleAddRegistration}
              className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700"
            >
              Adicionar Inscrição
            </button>
          </div>
          <ul className="space-y-4">
            {registrations.map((registration) => (
              <li
                key={registration.id}
                className="border p-4 rounded shadow flex justify-between items-center"
              >
                <div>
                  <p><strong>Usuário:</strong> {registration.userName}</p>
                  <p><strong>Email:</strong> {registration.userEmail}</p>
                </div>
                <button
                  onClick={() => handleRemoveRegistration(registration.id)}
                  className="bg-red-500 text-white py-1 px-4 rounded hover:bg-red-700"
                >
                  Remover
                </button>
              </li>
            ))}
          </ul>
        </>
      )}
    </div>
  );
};

export default RegistrationManagement;
