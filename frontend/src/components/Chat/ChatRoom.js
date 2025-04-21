import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const ChatRoom = ({ roomId, history, username, onLeaveRoom }) => {
  const [messages, setMessages] = useState(history);
  const [newMessage, setNewMessage] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const stompClient = useRef(null);
  const messagesEndRef = useRef(null);

  // Conexión STOMP sobre SockJS
  useEffect(() => {
    const token = localStorage.getItem('authToken');
    const socketUrl = 'http://localhost:8080/ws';
    const client = new Client({
      webSocketFactory: () => new SockJS(socketUrl),
      connectHeaders: { Authorization: `Bearer ${token}` },
      debug: () => {},
      onConnect: () => {
        setIsConnected(true);
        client.subscribe(`/topic/chat/${roomId}`, (msg) => {
          const received = JSON.parse(msg.body);
          setMessages(prev => [...prev, received]);
        });
      },
      onStompError: (err) => console.error('STOMP error:', err),
      onDisconnect: () => setIsConnected(false)
    });

    client.activate();
    stompClient.current = client;

    return () => {
      stompClient.current?.deactivate();
    };
  }, [roomId]);

  // Scroll al último mensaje
  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  const handleSendMessage = (e) => {
    e.preventDefault();
    if (!newMessage.trim() || !isConnected) return;
    const message = { sender: username, content: newMessage, type: 'CHAT' };
    stompClient.current.publish({
      destination: `/app/chat/${roomId}/sendMessage`,
      body: JSON.stringify(message)
    });
    setNewMessage('');
  };

  return (
    <div className="w-[90vw] h-[85vh] mx-auto my-2 bg-white rounded-xl shadow-xl overflow-hidden flex flex-col">
      <div className="flex justify-between items-center p-3 border-b border-gray-200">
        <h2 className="text-xl font-bold text-gray-900">Sala: {roomId}</h2>
        <button
          onClick={onLeaveRoom}
          className="px-4 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition-colors"
        >
          Salir
        </button>
      </div>

      <div className="flex-1 overflow-y-auto p-4 space-y-3">
        {messages.map((msg, index) => (
          <div key={index} className={`flex ${msg.sender === username ? 'justify-end' : 'justify-start'}`}>
            <div className={`max-w-[70%] px-4 py-2 rounded-xl ${msg.sender === username ? 'bg-black text-white' : 'bg-gray-100 border border-gray-200'}`}>
              <div className="text-sm font-medium">{msg.sender}</div>
              <div className="mt-1 text-base">{msg.content}</div>
              <div className="text-xs mt-1 opacity-70">
                {new Date(msg.timestamp).toLocaleTimeString()}
              </div>
            </div>
          </div>
        ))}
        <div ref={messagesEndRef} />
      </div>

      <form onSubmit={handleSendMessage} className="p-3 border-t border-gray-200">
        <div className="flex space-x-2">
          <input
            type="text"
            value={newMessage}
            onChange={(e) => setNewMessage(e.target.value)}
            className="flex-1 px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent"
            placeholder="Escribe un mensaje..."
          />
          <button
            type="submit"
            disabled={!isConnected}
            className={`px-4 py-2 rounded-lg text-white ${isConnected ? 'bg-black hover:bg-gray-800' : 'bg-gray-400'} transition-colors`}
          >
            Enviar
          </button>
        </div>
      </form>
    </div>
  );
};

export default ChatRoom;