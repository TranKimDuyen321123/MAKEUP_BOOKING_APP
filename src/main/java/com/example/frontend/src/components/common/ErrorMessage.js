import React from 'react';

const ErrorMessage = ({ message }) => {
  return <div style={{ color: 'red' }}>Lỗi: {message}</div>;
};

export default ErrorMessage;