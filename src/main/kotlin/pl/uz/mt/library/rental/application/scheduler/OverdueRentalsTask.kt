package pl.uz.mt.library.rental.application.scheduler

import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import pl.uz.mt.library.rental.application.service.RentalService

@Singleton
class OverdueRentalsTask(private val rentalService: RentalService) {

    @Scheduled(initialDelay = "15s", fixedDelay = "1m")
    fun run() = rentalService.overdueRentals()
}
