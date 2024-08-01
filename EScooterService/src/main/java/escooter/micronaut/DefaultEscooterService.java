/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package escooter.micronaut;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton // <1>
class DefaultEscooterService implements EScooterService {

    private final EscooterRepository escooterRepository;
    private final EScooterMapper mapper = new EScooterMapper();

    public DefaultEscooterService(final EscooterRepository escooterRepository) {
        this.escooterRepository = escooterRepository;
    }

    public Iterable<EScooterInfo> list() {
        return this.escooterRepository.findAll().stream()
                .map(this.mapper::convert).toList();
    }

    @Override
    public EScooter save(final EScooterInfo info) {
        if (info.getId()== null) {
            EScooter res = null;
            try {
                res = this.escooterRepository.save(this.mapper.revert(info));
            }catch (final Exception e) {
                e.printStackTrace();
            }
            return res;
        } else {
            return this.escooterRepository.update(this.mapper.revert(info));
        }
    }

    @Override
    public Optional<EScooterInfo> find(@NonNull final String id) {
        try {
            return Optional.of(this.mapper.convert(this.escooterRepository
                    .findById(id).get()));
        } catch (final Exception e) {
            return Optional.empty();
        }
    }

}
