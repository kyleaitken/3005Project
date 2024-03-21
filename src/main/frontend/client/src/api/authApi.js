import { API_ENDPOINTS } from './config';
import { post } from './utils';

export const attemptLogin = (email, password) => {
    return post(API_ENDPOINTS.login, { email, password });
};

