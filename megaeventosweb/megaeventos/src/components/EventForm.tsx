import React, { useState } from 'react';
import api from '../api/axiosConfig';

interface EventFormProps {
  event?: {
    id?: number;
    name: string;
    location: string;
    date: string;
    type: string;
    availableSpots: number;
  };
  onSubmit: () => void;
}

const EventForm: React.FC<EventFormProps> = ({ event, onSubmit }) => {
  const [formData, setFormData] = useState(
    event || { name: '', location: '', date: '', type: '', availableSpots: 0 }
  );

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (event?.id) {
        await api.put(`/events/${event.id}`, formData);
      } else {
        await api.post('/events', formData);
      }
      onSubmit();
    } catch (error) {
      console.error('Error saving event:', error);
    }
  };

  return (
    <form className="p-4 border rounded shadow" onSubmit={handleSubmit}>
      <h2 className="text-xl font-bold mb-4">
        {event ? 'Editar Evento' : 'Criar Novo Evento'}
      </h2>
      <div className="mb-4">
        <label className="block mb-2">Nome</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          className="border p-2 rounded w-full"
          required
        />
      </div>
      <div className="mb-4">
        <label className="block mb-2">Local</label>
        <input
          type="text"
          name="location"
          value={formData.location}
          onChange={handleChange}
          className="border p-2 rounded w-full"
          required
        />
      </div>
      <div className="mb-4">
        <label className="block mb-2">Data</label>
        <input
          type="date"
          name="date"
          value={formData.date}
          onChange={handleChange}
          className="border p-2 rounded w-full"
          required
        />
      </div>
      <div className="mb-4">
        <label className="block mb-2">Tipo</label>
        <input
          type="text"
          name="type"
          value={formData.type}
          onChange={handleChange}
          className="border p-2 rounded w-full"
          required
        />
      </div>
      <div className="mb-4">
        <label className="block mb-2">Vagas Disponíveis</label>
        <input
          type="number"
          name="availableSpots"
          value={formData.availableSpots}
          onChange={handleChange}
          className="border p-2 rounded w-full"
          required
        />
      </div>
      <button type="submit" className="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-700">
        Salvar
      </button>
    </form>
  );
};

export default EventForm;
