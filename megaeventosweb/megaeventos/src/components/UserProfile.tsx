import React, { useState, useEffect } from "react";
import { deleteEventFromUserProfile, getUserEnrolledEvents } from "../mock/mockData";


const UserProfile: React.FC = () => {
  const [userEvents, setUserEvents] = useState(() => getUserEnrolledEvents());

  const handleDeleteEvent = (eventId: string) => {
    try {
      deleteEventFromUserProfile(eventId);
      setUserEvents(getUserEnrolledEvents()); 
      alert("Evento removido do perfil com sucesso.");
    } catch (error: any) {
      alert(error.message);
    }
  };

  return (
    <div>
      <h1 className="text-3xl font-bold text-center mb-6 bg-gradient-to-b from-blue-500 to-black bg-clip-text text-transparent">Meu Perfil</h1>
      <h2 className="text-xl font-semibold mb-2">Meus Eventos</h2>

      {userEvents.length === 0 ? (
        <p className="text-gray-500">Você não tem eventos cadastrados.</p>
      ) : (
        <ul className="space-y-4">
          {userEvents.map((event) => (
            <li
              key={event.id}
              className="flex justify-between items-center bg-white shadow p-4 rounded"
            >
              <div>
                <p className="font-semibold">{event.name}</p>
                <p>{new Date(event.date).toLocaleDateString()}</p>
              </div>
              <button
                className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700"
                onClick={() => handleDeleteEvent(event.id)}
              >
                Deletar
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default UserProfile;
