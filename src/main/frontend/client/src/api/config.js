const BASE_URL = 'http://localhost:8080';

export const API_ENDPOINTS = {
    login: `${BASE_URL}/auth/login`,
    registerMember: () => `${BASE_URL}/members/register`,
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
    removeExerciseFromRoutine: (memberId, logId) => `${BASE_URL}/members/${memberId}/routines/removeExercise/${logId}`,
    getInProgressGoals: (memberId) => `${BASE_URL}/members/${memberId}/goals/incomplete`,
    getCompletedGoals: (memberId) => `${BASE_URL}/members/${memberId}/goals/completed`,
    completeGoal: (memberId, goalId) => `${BASE_URL}/members/${memberId}/goals/complete/${goalId}`,
    deleteGoal: (memberId, goalId) => `${BASE_URL}/members/${memberId}/goals/delete/${goalId}`,
    addGoal: (memberId) => `${BASE_URL}/members/${memberId}/goals/add`,
    getMemberPaidInvoices: (memberId) => `${BASE_URL}/members/${memberId}/invoices/paid`,
    getMemberUnpaidInvoices: (memberId) => `${BASE_URL}/members/${memberId}/invoices/unpaid`,
    getMemberProcessingInvoices: (memberId) => `${BASE_URL}/members/${memberId}/invoices/processing`,
    getMemberCancelledInvoices: (memberId) => `${BASE_URL}/members/${memberId}/invoices/cancelled`,
    payMemberInvoice: (paymentId) => `${BASE_URL}/members/invoices/pay/${paymentId}`,
    getMemberHealthInfo: (memberId) => `${BASE_URL}/memberHealthInfo/${memberId}`,
    updateMemberHealthInfo: (memberId) => `${BASE_URL}/memberHealthInfo/update/${memberId}`,
    getMemberProfile: (memberId) => `${BASE_URL}/members/${memberId}`,
    updateMemberProfile: (memberId) => `${BASE_URL}/members/updateProfile/${memberId}`
  };


export const ADMIN_API_ENDPOINTS = {
    getAllClasses: () => `${BASE_URL}/admin/classes`,
    updateClass: (classId) => `${BASE_URL}/admin/classes/update/${classId}`,
    addClass: () => `${BASE_URL}/admin/classes/add`,
    deleteClass: (classId) => `${BASE_URL}/admin/classes/delete/${classId}`,
    getRooms: () => `${BASE_URL}/admin/rooms`,
    getAllInvoices: () => `${BASE_URL}/admin/invoices`,
    getProcessingInvoices: () => `${BASE_URL}/admin/invoices/processing`,
    processInvoice: (paymentId) => `${BASE_URL}/admin/invoices/${paymentId}`,
    getEquipment: () => `${BASE_URL}/admin/equipment`,
    repairEquipment: (equipmentId) => `${BASE_URL}/admin/equipment/repair/${equipmentId}`,
    reportEquipmentBroken: (equipmentId) => `${BASE_URL}/admin/equipment/reportBroken/${equipmentId}`
};

export const TRAINER_API_ENDPOINTS = {
  getTrainer: (trainerId) => `${BASE_URL}/trainers/${trainerId}`,
  getUpcomingSessions: (trainerId) => `${BASE_URL}/trainers/${trainerId}/upcomingSessions`,
  getPastSessions: (trainerId) => `${BASE_URL}/trainers/${trainerId}/pastSessions`,
  deleteSession: (sessionId) => `${BASE_URL}/trainers/deleteSession/${sessionId}`,
  updateSchedule: (trainerId) => `${BASE_URL}/trainers/${trainerId}/schedule`,
  getMembers: () => `${BASE_URL}/trainers/members`,
  getMember: (memberId) => `${BASE_URL}/trainers/members/${memberId}`,
}