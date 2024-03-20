import React, { createContext, useContext, useReducer, useState } from 'react';

const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext);

export const authReducer = (state, action) => {
  switch (action.type) {
    case 'LOGIN':
      return { userId: action.payload.id, 
        userType: action.payload.type }
    case 'LOGOUT':
      return {
        userId: null, userType: null
      }
    default:
      return state
  }
}

export const AuthContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, {
    userId: null, userType: null
  })

  console.log('AuthContext state: ', state);

  return (
    <AuthContext.Provider value={{...state, dispatch}}>
      { children }
    </AuthContext.Provider>
  )

};
