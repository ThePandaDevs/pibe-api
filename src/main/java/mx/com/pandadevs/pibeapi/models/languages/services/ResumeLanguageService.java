package mx.com.pandadevs.pibeapi.models.languages.services;
// Java
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Spring

import mx.com.pandadevs.pibeapi.models.languages.mapper.LanguageMapper;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import mx.com.pandadevs.pibeapi.models.resumes.ResumeService;
import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Models
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.languages.mapper.ResumeLanguageMapper;
import mx.com.pandadevs.pibeapi.models.languages.repository.ResumeLanguageRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

import javax.transaction.Transactional;

@Service
public class ResumeLanguageService implements ServiceInterface<Integer, ResumeLanguageDto> {
    private final ResumeLanguageMapper mapper;
    private final ResumeMapper resumeMapper;

    private  final LanguageMapper languageMapper;
    @Autowired
    private ResumeLanguageRepository languageRepository;
    @Autowired
    private ResumeService resumeService;

    public ResumeLanguageService(ResumeLanguageMapper mapper, ResumeMapper resumeMapper, LanguageMapper languageMapper) {
        this.mapper = mapper;
        this.resumeMapper = resumeMapper;
        this.languageMapper = languageMapper;
    }

    @Override
    public List<ResumeLanguageDto> getAll() {
        return mapper.toResumeLanguagesDto(languageRepository.findAll());
    }

    @Override
    public Optional<ResumeLanguageDto> getById(Integer id) {
        Optional<ResumeLanguage> resumeLanguage = languageRepository.findById(id);
        return resumeLanguage.map(entity -> {
            return Optional.of(mapper.toResumeLanguageDto(entity));
        }).orElse(Optional.empty());
    }
    public List<ResumeLanguageDto> getByResumeId(Integer id) {
        return mapper.toResumeLanguagesDto(languageRepository.findAllByResumeId(id));
    }
    @Override
    public ResumeLanguageDto save(ResumeLanguageDto entity) {
        return mapper.toResumeLanguageDto(languageRepository.save(mapper.toResumeLanguage(entity)));
    }

    public void save(ResumeLanguageDto entity, ResumeDto resume) {
        Optional<ResumeDto> resumeDto = resumeService.getById(resume.getId());
        resumeDto.map(present -> {
            Resume toResume = resumeMapper.toResume(present);
            ResumeLanguage  resumeLanguage = new ResumeLanguage(
                    entity.getId(),
                    entity.getLevel(),
                    entity.getActive(),
                    toResume,
            languageMapper.toLanguage(entity.getLanguage()));
        return mapper.toResumeLanguageDto(languageRepository.save(resumeLanguage));
        });
    }

    @Transactional
    public List<ResumeLanguageDto> save(List<ResumeLanguageDto> entities,ResumeDto resume) {
        for (ResumeLanguageDto resumeLanguage: entities) save(resumeLanguage, resume);
        return getByResumeId(resume.getId());
    }

    @Override
    public Optional<ResumeLanguageDto> update(ResumeLanguageDto entity) {
        Optional<ResumeLanguage> updatedEntity = languageRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            return Optional.of(mapper.toResumeLanguageDto(
                    languageRepository.saveAndFlush(
                            mapper.toResumeLanguage(entity))));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<ResumeLanguageDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<ResumeLanguage> updatedEntity = languageRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(ResumeLanguage.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toResumeLanguageDto(languageRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return languageRepository.findById(id).map(entity -> {
            entity.setActive(false);
            languageRepository.save(entity);
            return true;
        }).orElse(false);
    }
}
