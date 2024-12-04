import { useParams } from "react-router-dom";
import { enrollInEvent, getEventById } from "../mock/mockData";


const EventDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const event = getEventById(id!);

  if (!event) {
    return <p>Evento não encontrado.</p>;
  }

  const handleEnroll = () => {
    try {
      const message = enrollInEvent(event.id);
      alert(message);
    } catch (error: any) {
      alert(error.message);
    }
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold">{event.name}</h1>
      <p>{new Date(event.date).toLocaleDateString()}</p>
      <p>{event.location}</p>
      <p>Tipo: {event.type}</p>
      <p>Vagas disponíveis: {event.spots}</p>
      <button
        onClick={handleEnroll}
        className="mt-4 bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        Inscrever-se
      </button>
    </div>
  );
};

export default EventDetails;
