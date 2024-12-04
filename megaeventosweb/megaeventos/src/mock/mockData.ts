export const mockEvents = [
    {
      id: "1",
      name: "Workshop de React",
      date: "2024-11-30T14:00:00Z",
      location: "São Paulo",
      type: "Tecnologia",
      spots: 50,
    },
    {
      id: "2",
      name: "Conferência de UX Design",
      date: "2024-12-05T10:00:00Z",
      location: "Rio de Janeiro",
      type: "Design",
      spots: 30,
    },
    {
      id: "3",
      name: "Maratona de Programação",
      date: "2024-12-10T09:00:00Z",
      location: "Online",
      type: "Competição",
      spots: 100,
    },
    {
      id: "4",
      name: "Seminário de Inteligência Artificial",
      date: "2024-12-15T16:00:00Z",
      location: "Belo Horizonte",
      type: "Tecnologia",
      spots: 60,
    },
    {
      id: "5",
      name: "Oficina de Escrita Criativa",
      date: "2024-11-20T18:00:00Z",
      location: "Curitiba",
      type: "Literatura",
      spots: 20,
    },
    {
      id: "6",
      name: "Hackathon de Startups",
      date: "2024-12-25T08:00:00Z",
      location: "Online",
      type: "Empreendedorismo",
      spots: 80,
    },
    {
      id: "7",
      name: "Exposição de Arte Contemporânea",
      date: "2024-12-02T14:00:00Z",
      location: "Porto Alegre",
      type: "Arte",
      spots: 40,
    },
    {
      id: "8",
      name: "Palestra de Marketing Digital",
      date: "2024-11-28T19:00:00Z",
      location: "Salvador",
      type: "Marketing",
      spots: 25,
    },
    {
      id: "9",
      name: "Fórum de Sustentabilidade",
      date: "2024-12-18T09:30:00Z",
      location: "Manaus",
      type: "Meio Ambiente",
      spots: 70,
    },
    {
      id: "10",
      name: "Curso de Edição de Vídeo",
      date: "2024-11-27T15:00:00Z",
      location: "Online",
      type: "Produção Audiovisual",
      spots: 35,
    },
    {
      id: "11",
      name: "Encontro de Desenvolvedores Fullstack",
      date: "2024-12-12T13:00:00Z",
      location: "Florianópolis",
      type: "Tecnologia",
      spots: 50,
    },
    {
      id: "12",
      name: "Workshop de Fotografia Profissional",
      date: "2024-11-25T10:00:00Z",
      location: "Recife",
      type: "Fotografia",
      spots: 15,
    },
  ];
  
  export const mockUser = {
    id: "123",
    name: "Ellen Mariza",
    email: "ellenmarizar@example.com",
    enrolledEvents: ["1"],
  };
  
  // Função para buscar todos os eventos
  export const getEvents = () => mockEvents;
  
  // Função para buscar detalhes de um evento pelo ID
  export const getEventById = (id: string) => {
    return mockEvents.find((event) => event.id === id);
  };
  
  // Função para inscrever-se em um evento
  export const enrollInEvent = (eventId: string) => {
    const event = getEventById(eventId);
    if (!event) {
      throw new Error("Evento não encontrado.");
    }
  
    if (mockUser.enrolledEvents.includes(eventId)) {
      throw new Error("Você já está inscrito neste evento.");
    }
  
    mockUser.enrolledEvents.push(eventId);
    event.spots -= 1;
  
    return `Inscrição confirmada no evento: ${event.name}`;
  };
  
  // Função para listar eventos no perfil do usuário
  export const getUserEnrolledEvents = () => {
    return mockEvents.filter((event) => mockUser.enrolledEvents.includes(event.id));
  };
  
  // Função para deletar um evento do perfil do usuário
  export const deleteEventFromUserProfile = (eventId: string) => {
    if (!mockUser.enrolledEvents.includes(eventId)) {
      throw new Error("Evento não encontrado no perfil do usuário.");
    }
  
    mockUser.enrolledEvents = mockUser.enrolledEvents.filter((id) => id !== eventId);
    return `Evento com ID ${eventId} removido do perfil.`;
  };
  