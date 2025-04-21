import React, { useState, useEffect } from 'react';
import AuthLayout from './components/Auth/AuthLayout';
import LoginForm from './components/Auth/LoginForm';
import RegisterForm from './components/Auth/RegisterForm';
import RoomSelector from './components/Chat/RoomSelector';
import ChatRoom from './components/Chat/ChatRoom';
import ProtectedRoute from './components/App/ProtectedRoute';

const App = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [isLoading, setIsLoading] = useState(true);
  const [showRegister, setShowRegister] = useState(false);
  const [currentRoom, setCurrentRoom] = useState(null);
  const [roomHistory, setRoomHistory] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    const storedUsername = localStorage.getItem('username');
    
    if (token && storedUsername) {
      setIsAuthenticated(true);
      setUsername(storedUsername);
    }
    setIsLoading(false);
  }, []);

  const handleLoginSuccess = (user) => {
    setUsername(user);
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    localStorage.removeItem('username');
    setIsAuthenticated(false);
    setUsername('');
    setCurrentRoom(null);
  };

  const toggleAuthView = () => {
    setShowRegister(!showRegister);
  };

  const handleJoinRoom = (roomId, history) => {
    setCurrentRoom(roomId);
    setRoomHistory(history);
  };

  const handleLeaveRoom = () => {
    setCurrentRoom(null);
    setRoomHistory([]);
  };

  if (isLoading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-black"></div>
      </div>
    );
  }

  return (
    <>
      {!isAuthenticated ? (
        <AuthLayout>
          {showRegister ? (
            <RegisterForm onRegisterSuccess={handleLoginSuccess} switchToLogin={toggleAuthView} />
          ) : (
            <LoginForm onLoginSuccess={handleLoginSuccess} switchToRegister={toggleAuthView} />
          )}
        </AuthLayout>
      ) : (
        <ProtectedRoute isAuthenticated={isAuthenticated}>
          {!currentRoom ? (
            <AuthLayout>
              <RoomSelector 
                onJoinRoom={handleJoinRoom} 
                username={username} 
                onLogout={handleLogout} 
              />
            </AuthLayout>
          ) : (
            <ChatRoom 
              roomId={currentRoom} 
              history={roomHistory} 
              username={username} 
              onLeaveRoom={handleLeaveRoom} 
            />
          )}
        </ProtectedRoute>
      )}
    </>
  );
};

export default App;

// DONE