package group1.googlebooks.api;

import group1.googlebooks.exception.ResourceNotFoundException;
import group1.googlebooks.model.Volume;
import group1.googlebooks.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class VolumeController {

    @Autowired
    private VolumeRepository volumeRepository;

    @GetMapping("volumes")
    public List<Volume> getAllVolumes() {
        return this.volumeRepository.findAll();
    }

    @GetMapping("/volumes/{id}")
    public ResponseEntity<Volume> getVolumeById(@PathVariable(value = "id") Long volumeId) throws ResourceNotFoundException {
        Volume volume = this.volumeRepository.findById(volumeId).orElseThrow(() -> new ResourceNotFoundException("Volume not found for id " + volumeId));
        return ResponseEntity.ok().body(volume);
    }

    @PostMapping("volumes")
    public Volume createVolume(@RequestBody Volume volume) {
        return this.volumeRepository.save(volume);
    }

    @PutMapping("volumes/{id}")
    public ResponseEntity<Volume> updateVolume(@PathVariable(value = "id") Long volumeId, @RequestBody Volume volumeDetails) throws ResourceNotFoundException {
        Volume volume = this.volumeRepository.findById(volumeId).orElseThrow(() -> new ResourceNotFoundException("Volume not found for id " + volumeId));
        volume.setGoogleId(volumeDetails.getGoogleId());
        volume.setKind(volumeDetails.getKind());
        return ResponseEntity.ok(this.volumeRepository.save(volume));
    }

    @DeleteMapping("volumes/{id}")
    public Map<String, Boolean> deleteVolume(@PathVariable(value = "id") Long volumeId) throws ResourceNotFoundException {
        Volume volume = this.volumeRepository.findById(volumeId).orElseThrow(() -> new ResourceNotFoundException("Volume not found for id " + volumeId));
        this.volumeRepository.delete(volume);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
