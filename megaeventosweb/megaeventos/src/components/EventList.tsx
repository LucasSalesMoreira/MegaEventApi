import React, { useState } from "react";
import { Link } from "react-router-dom";
import { getEvents } from "../mock/mockData";

const EventList: React.FC = () => {
  const [events, setEvents] = useState(() => getEvents());
  const [selectedLocation, setSelectedLocation] = useState<string | null>(null);

  const locations = Array.from(new Set(events.map((event: { location: any; }) => event.location)));

  const handleFilterByLocation = (location: string | null) => {
    if (location) {
      setSelectedLocation(location);
      setEvents(getEvents().filter((event: { location: string; }) => event.location === location));
    } else {
      setSelectedLocation(null);
      setEvents(getEvents());
    }
  };

  return (
    <div>
      <h1 className="text-3xl font-bold text-center mb-6 bg-gradient-to-b from-blue-500 to-black bg-clip-text text-transparent">
        Eventos Disponíveis
      </h1>

      {/* Botões de Filtragem */}
      <div className="flex justify-center gap-4 mb-6">
        <button
          onClick={() => handleFilterByLocation(null)}
          className={`px-4 py-2 rounded-lg ${
            selectedLocation === null
              ? "bg-blue-600 text-white"
              : "bg-gray-200 text-gray-600"
          } hover:bg-blue-500 hover:text-white`}
        >
          Todos
        </button>
        {locations.map((location) => (
          <button
            key={location}
            onClick={() => handleFilterByLocation(location)}
            className={`px-4 py-2 rounded-lg ${
              selectedLocation === location
                ? "bg-blue-600 text-white"
                : "bg-gray-200 text-gray-600"
            } hover:bg-blue-500 hover:text-white`}
          >
            {location}
          </button>
        ))}
      </div>

      {/* Lista de Eventos */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        {events.length > 0 ? (
          events.map((event) => (
            <div
              key={event.id}
              className="bg-white rounded-lg shadow-lg p-4 flex flex-col justify-between"
            >
              <div>
                <h2 className="text-lg font-bold text-blue-600">{event.name}</h2>
                <p className="text-gray-500">
                  {new Date(event.date).toLocaleDateString()}
                </p>
                <p className="text-gray-700">{event.location}</p>
                <p className="text-gray-500 text-sm">{event.type}</p>
              </div>
              <Link
                to={`/event/${event.id}`}
                className="mt-4 bg-blue-600 text-white text-center py-2 rounded-lg hover:bg-blue-700"
              >
                Ver Detalhes
              </Link>
            </div>
          ))
        ) : (
          <p className="text-gray-500 col-span-full text-center">
            Nenhum evento disponível para a localização selecionada.
          </p>
        )}
      </div>
    </div>
  );
};

export default EventList;
