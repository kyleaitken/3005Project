const BASE_URL = 'http://localhost:8080';

export const API_ENDPOINTS = {
    login: `${BASE_URL}/auth/login`,
    memberSchedule: (memberId) => `${BASE_URL}/members/${memberId}/schedule`
  };