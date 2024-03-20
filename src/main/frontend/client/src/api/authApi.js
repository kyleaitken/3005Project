import { API_ENDPOINTS } from './config';
import { post } from './utils';

// export const attemptLogin = async (email, password) => {  
//     try {
//       const response = await fetch(API_ENDPOINTS.login, {
//         method: 'POST',
//         headers: {
//           'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({ email, password }),
//       });
  
//       if (response.ok) {
//         const loginResponse = await response.json();
//         return loginResponse; 
//       } else {
//         console.error("Login failed:", response.status, response.statusText);
//         return null; 
//       }
//     } catch (error) {
//       console.error("Network error:", error);
//       return null;
//     }
//   };

export const attemptLogin = (email, password) => {
    return post(API_ENDPOINTS.login, { email, password });
};