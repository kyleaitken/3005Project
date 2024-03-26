import { TRAINER_API_ENDPOINTS } from './config';
import { get, del, post, put, putWithBody} from './utils';

export const getTrainer = (trainerId) => {
    const url = TRAINER_API_ENDPOINTS.getTrainer(trainerId);
    return get(url);
};

export const getUpcomingSessions = (trainerId) => {
    const url = TRAINER_API_ENDPOINTS.getUpcomingSessions(trainerId);
    return get(url);
};

export const getPastSessions = (trainerId) => {
    const url = TRAINER_API_ENDPOINTS.getPastSessions(trainerId);
    return get(url);
};

export const deleteSession = (sessionId) => {
    const url = TRAINER_API_ENDPOINTS.deleteSession(sessionId);
    return del(url);
};

export const updateSchedule = (trainerId, scheduleInfo) => {
    return putWithBody(TRAINER_API_ENDPOINTS.updateSchedule(trainerId), scheduleInfo);
};
