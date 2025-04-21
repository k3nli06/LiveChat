import React from 'react';

const Dashboard = ({ username, onLogout }) => {
  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="py-12">
          <div className="flex justify-between items-center">
            <h1 className="text-3xl font-bold text-gray-900">Bienvenido, {username}!</h1>
            <button
              onClick={onLogout}
              className="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-colors"
            >
              Cerrar sesión
            </button>
          </div>
          <p className="mt-4 text-lg text-gray-600">
            Has iniciado sesión correctamente en nuestro sistema seguro.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;