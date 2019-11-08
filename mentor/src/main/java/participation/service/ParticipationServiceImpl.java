package participation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import participation.dao.ParticipationDAO;

@Service
public class ParticipationServiceImpl implements ParticipationService {
	@Autowired
	private ParticipationDAO participationDAO;
}
