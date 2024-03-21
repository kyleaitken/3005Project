import React, { createContext, useContext, useReducer, useState } from 'react';

const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext);

export const authReducer = (state, action) => {
  switch (action.type) {
    case 'LOGIN':
      localStorage.setItem('user', JSON.stringify({ userId: action.payload.id, userType: action.payload.type }));
      return { userId: action.payload.id, 
        userType: action.payload.type }
    case 'LOGOUT':
      localStorage.removeItem('user');
      return {
        userId: null, userType: null
      }
    default:
      return state
  }
}

export const AuthContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, {
    userId: null, userType: null, ...JSON.parse(localStorage.getItem('user'))
  })
  const isLoggedIn = state.userId !== null; 

  console.log('AuthContext state: ', state);

  return (
    <AuthContext.Provider value={{...state, dispatch, isLoggedIn}}>
      { children }
    </AuthContext.Provider>
  )

};
