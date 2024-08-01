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
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller("api/escooters") // <1>
@ExecuteOn(TaskExecutors.BLOCKING)  // <2>
class EScooterController {

    private final EScooterService service;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    EScooterController(final EScooterService service) {  // <3>
        this.service = service;
    }

    @Get
    Iterable<EScooterInfo> list() {
        logger.info("Get all e-scooters requested");
        return this.service.list();
    }

    @Post
    @Status(HttpStatus.CREATED)

    EScooter save(@NonNull @Valid @Body final EScooterInfo eScooter) {
        //this.logger.info("Add new e-scooter request - {}", eScooter.toString());
        return this.service.save(eScooter);
    }

    @Put("/{id}")
    EScooter update(@PathVariable final String id, @NonNull @Valid @Body final EScooterInfo eScooter) {
        //this.logger.info("Update e-scooter request - {}", eScooter.toString());
        eScooter.setId(id);
        return this.service.save(eScooter);
    }

    @Get("/{id}")
    Optional<EScooterInfo> find(@PathVariable final String id) {
        //this.logger.info("Get e-scooter request - {}", id);
        return this.service.find(id);
    }

}
