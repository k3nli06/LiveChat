import React from 'react';

const ProtectedRoute = ({ children, isAuthenticated }) => {
  if (!isAuthenticated) {
    return null;
  }

  return children;
};

export default ProtectedRoute;