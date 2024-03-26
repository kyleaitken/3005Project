import { API_ENDPOINTS } from './config';
import { get, del, post, put, putWithBody} from './utils';

export const getMemberSchedule = (memberId) => {
    const url = API_ENDPOINTS.memberSchedule(memberId);
    return get(url);
};

export const getAvailableTrainers = () => {
    const url = API_ENDPOINTS.getAvailableTrainers();
    return get(url);
}

export const getMemberUpcomingSessions = (memberId) => {
    const url = API_ENDPOINTS.getMemberUpcomingSessions(memberId);
    return get(url);
}

export const getMemberPastSessions = (memberId) => {
    const url = API_ENDPOINTS.getMemberPastSessions(memberId);
    return get(url);
}

export const cancelMemberSession = (sessionId) => {
    const url = API_ENDPOINTS.cancelMemberSession(sessionId);
    return del(url);
}

export const getMemberUpcomingClasses = (memberId) => {
    const url = API_ENDPOINTS.getMemberUpcomingClasses(memberId);
    return get(url);
}

export const getMemberPastClasses = (memberId) => {
    const url = API_ENDPOINTS.getMemberPastClasses(memberId);
    return get(url);
}

export const getMemberAvailableClasses = (memberId) => {
    const url = API_ENDPOINTS.getMemberAvailableClasses(memberId);
    return get(url);
}

export const leaveClass = (memberId, classId) => {
    const url = API_ENDPOINTS.leaveClassById(memberId, classId);
    return del(url);
}

export const joinClass = (memberId, classId) => {
    const url = API_ENDPOINTS.joinClassById(memberId, classId);
    return post(url);
}

export const addSession = (memberId, trainerId, date, time) => {
    return post(API_ENDPOINTS.addMemberSession(memberId), {trainerId, date, time });
};

export const getExerciseRoutines = (memberId) => {
    const url = API_ENDPOINTS.getExerciseRoutines(memberId);
    return get(url);
}

export const addExerciseRoutine = (memberId) => {
    const url = API_ENDPOINTS.addExerciseRoutine(memberId);
    return post(url);
}

export const deleteExerciseRoutine = (memberId, routineId) => {
    const url = API_ENDPOINTS.deleteExerciseRoutine(memberId, routineId);
    return del(url);
}

export const addExerciseToRoutine = (userId, routineId, exerciseName, numSets, numReps, duration, weight) => {
    return post(API_ENDPOINTS.addExerciseToRoutine(userId, routineId), { exerciseName, numSets, numReps, duration, weight });
}

export const removeExerciseFromRoutine = (userId, logId) => {
    const url = API_ENDPOINTS.removeExerciseFromRoutine(userId, logId);
    return del(url);
}

export const getCompletedGoals = (memberId) => {
    const url = API_ENDPOINTS.getCompletedGoals(memberId);
    return get(url);
}

export const getInProgressGoals = (memberId) => {
    const url = API_ENDPOINTS.getInProgressGoals(memberId);
    return get(url);
}

export const setGoalComplete = (memberId, goalId) => {
    const url = API_ENDPOINTS.completeGoal(memberId, goalId);
    return put(url);
}

export const deleteGoal = (userId, goalId) => {
    const url = API_ENDPOINTS.deleteGoal(userId, goalId);
    return del(url);
}

export const addGoal = (userId, description, targetDate) => {
    return post(API_ENDPOINTS.addGoal(userId), { description, targetDate });
}

export const getMemberPaidInvoices = (memberId) => {
    const url = API_ENDPOINTS.getMemberPaidInvoices(memberId);
    return get(url);
}

export const getMemberUnpaidInvoices = (memberId) => {
    const url = API_ENDPOINTS.getMemberUnpaidInvoices(memberId);
    return get(url);
}

export const getMemberProcessingInvoices = (memberId) => {
    const url = API_ENDPOINTS.getMemberProcessingInvoices(memberId);
    return get(url);
}

export const getMemberCancelledInvoices = (memberId) => {
    const url = API_ENDPOINTS.getMemberCancelledInvoices(memberId);
    return get(url);
}

export const payMemberInvoice = (paymentId) => {
    const url = API_ENDPOINTS.payMemberInvoice(paymentId);
    return put(url);
}

export const getMemberHealthInfo = (memberId) => {
    const url = API_ENDPOINTS.getMemberHealthInfo(memberId);
    return get(url);
}

export const updateMemberHealthInfo = (memberId, healthInfo) => {
    return putWithBody(API_ENDPOINTS.updateMemberHealthInfo(memberId), healthInfo);
};

export const getMemberProfile = (memberId) => {
    const url = API_ENDPOINTS.getMemberProfile(memberId);
    return get(url);
};

export const updateMemberProfile = (memberId, profileInfo) => {
    return putWithBody(API_ENDPOINTS.updateMemberProfile(memberId), profileInfo);
};

export const registerMember = (memberInfo) => {
    console.log('api', memberInfo);
    return post(API_ENDPOINTS.registerMember(), memberInfo);
}