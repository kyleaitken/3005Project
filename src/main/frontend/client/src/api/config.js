const BASE_URL = 'http://localhost:8080';

export const API_ENDPOINTS = {
    login: `${BASE_URL}/auth/login`,
    memberSchedule: (memberId) => `${BASE_URL}/members/${memberId}/schedule`,
    getAvailableTrainers: () => `${BASE_URL}/members/getTrainers`,
    getMemberUpcomingSessions: (memberId) => `${BASE_URL}/members/${memberId}/futureSessions`,
    getMemberPastSessions: (memberId) => `${BASE_URL}/members/${memberId}/pastSessions`,
    cancelMemberSession: (sessionId) => `${BASE_URL}/members/sessions/${sessionId}`,
    getMemberUpcomingClasses: (memberId) => `${BASE_URL}/members/${memberId}/futureClasses`,
    getMemberAvailableClasses: (memberId) => `${BASE_URL}/members/${memberId}/availableClasses`,
    getMemberPastClasses: (memberId) => `${BASE_URL}/members/${memberId}/pastClasses`,
    leaveClassById: (memberId, classId) => `${BASE_URL}/members/${memberId}/leaveClass/${classId}`,
    joinClassById: (memberId, classId) => `${BASE_URL}/members/${memberId}/join/${classId}`,
    addMemberSession: (memberId) => `${BASE_URL}/members/${memberId}/addSession`,
    getExerciseRoutines: (memberId) => `${BASE_URL}/members/${memberId}/routines/exercises`,
    addExerciseRoutine: (memberId) => `${BASE_URL}/members/${memberId}/routines/add`,
    deleteExerciseRoutine: (memberId, routineId) => `${BASE_URL}/members/${memberId}/routines/delete/${routineId}`,
    addExerciseToRoutine: (memberId, routineId) => 
      `${BASE_URL}/members/${memberId}/routines/${routineId}/addExercise`,

  };